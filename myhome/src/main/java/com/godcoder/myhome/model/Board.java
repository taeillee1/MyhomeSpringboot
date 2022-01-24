package com.godcoder.myhome.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity //데이터베이스 연동을 위한 모델클라스라는것을 정의
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=2, max=30)
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
