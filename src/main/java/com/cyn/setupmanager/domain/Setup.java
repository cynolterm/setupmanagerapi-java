package com.cyn.setupmanager.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="setups")
public class Setup
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonManagedReference
    @OneToMany(mappedBy = "setup")
    private List<SetupVariant> setupVariants;
}
