package com.oyaerdayi.entity;




import javax.persistence.*;

@Entity
@Table(name="USER_")
public class User {

    @SequenceGenerator(name = "generator", sequenceName = "USER_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;
}
