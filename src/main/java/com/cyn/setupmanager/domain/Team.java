package com.cyn.setupmanager.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="teams")
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer ownerId;

    @JsonManagedReference
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<User> members;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<Setup> setups;

    private String name;

    private String description;
}
