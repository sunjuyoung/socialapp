package com.example.socialApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Stories extends BaseTimes {

    @Id
    @GeneratedValue
    @Column(name = "stories_id")
    private Long id;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

}
