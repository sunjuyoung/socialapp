package com.example.socialApi.service;

import com.example.socialApi.dto.CreatePostDTO;
import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.dto.UploadFileDTO;
import com.example.socialApi.dto.UploadResultDTO;
import com.example.socialApi.model.Posts;
import com.example.socialApi.model.PostsPhoto;
import com.example.socialApi.model.Users;
import com.example.socialApi.repository.PostsPhotoRepository;
import com.example.socialApi.repository.PostRepository;
import com.example.socialApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostsPhotoRepository postsPhotoRepository;
    private final UserRepository userRepository;

    @Value("${com.example.socialapp.path}")
    private String uploadPath;

    public List<PostDTO> getPosts(Long id,Long profileUserId) {
        List<PostDTO> postDTOList = new ArrayList<>();
        if(profileUserId != null){
            postDTOList = postRepository.findAllByUserId(profileUserId);
        }else{
            postDTOList = postRepository.findAllWithUserNameAndLike(id);
        }
        return postDTOList;
    }

    public List<PostDTO> getLatelyPosts(Long id) {
        List<Posts> top3 = postRepository.findTop3ByUsersOrderByCreatedByDesc(new Users(id));
        List<PostDTO> postDTOS = new ArrayList<>();
        top3.stream().forEach(post -> {
            postDTOS.add(new PostDTO(post.getId(),
                    post.getDescription(),
                    post.getUsers().getNickname(),
                    post.getUsers().getId(),
                    post.getUsers().getProfilePic(),
                    post.getCreatedBy()));
        });

        return postDTOS;
    }

    public Long uploadImage(MultipartFile file) throws IOException {
        PostsPhoto save = postsPhotoRepository.save(PostsPhoto.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(compressImage(file.getBytes()))
                .build());

        if(save != null){
            return save.getId();
        }
        return null;
    }

    public byte[] getImage(String fileName) {
        Optional<PostsPhoto> postImage = postsPhotoRepository.findByName(fileName);
        byte[] image = decompressImage(postImage.get().getImageData());
        return image;
    }

/*    public void createPost(CreatePostDTO createPostDTO, Long userId, Long photoId) {
        Optional<Users> users = userRepository.findById(userId);
        Optional<PostsPhoto> photo = postsPhotoRepository.findById(photoId);
        Posts posts;
       if(photo.isPresent()){
           posts = Posts.builder()
                   .description(createPostDTO.getDescription())
                   .users(users.get())
                   .postsPhoto(photo.get())
                   .build();
        }else{
           posts = Posts.builder()
                   .description(createPostDTO.getDescription())
                   .users(users.get())
                   .build();

       }
        postRepository.save(posts);
    }*/

    public void createPosts(CreatePostDTO createPostDTO, Long userId) {
        Optional<Users> users = userRepository.findById(userId);
        Posts posts = Posts.builder()
                .description(createPostDTO.getDescription())
                .users(users.get())
                .img(createPostDTO.getImg())
                .build();
        postRepository.save(posts);
    }

    public Long uploadImagePost(MultipartFile file, Long postId) {

        return null;
    }

    public  byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
        }
        return outputStream.toByteArray();
    }


    public UploadResultDTO uploadFile(MultipartFile files) {
        if(files != null){
                String originalName = files.getOriginalFilename();
                log.info(originalName);

                String uuid = UUID.randomUUID().toString();

                Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

                boolean image = false;

                try {
                    files.transferTo(savePath);

                    //이미지 파일의 종류라면
                    if(Files.probeContentType(savePath).startsWith("image")){
                        image = true;

                    }

                } catch ( IOException e) {
                    e.printStackTrace();
                }

               UploadResultDTO uploadResultDTO = UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .img(image).build();

            return uploadResultDTO;
        }//end if

        return null;

    }


}
