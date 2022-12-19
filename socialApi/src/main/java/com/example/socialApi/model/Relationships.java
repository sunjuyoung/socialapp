package com.example.socialApi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Relationships  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relationships_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Users followUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users followedUser;

}
