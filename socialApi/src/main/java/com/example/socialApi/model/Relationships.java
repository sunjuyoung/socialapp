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
public class Relationships  {

    @Id
    @GeneratedValue
    @Column(name = "relationships_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Users followUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users followedUser;

}
