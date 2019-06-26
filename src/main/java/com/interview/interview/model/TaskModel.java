package com.interview.interview.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Table(name = "X")
@Entity
@Getter
@Setter
@ToString
public class TaskModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "A")
  private String aColumn;

  @Column(name = "B")
  private String bColumn;

  @Column(name = "C")
  private String cColumn;

  @Column(name = "D")
  private String dColumn;

  @Column(name = "E")
  @Size(max = 1355)
  private String eColumn;

  @Column(name = "F")
  private String fColumn;

  @Column(name = "G")
  private String gColumn;

  @Column(name = "H")
  private String hColumn;

  @Column(name = "I")
  private String iColumn;

  @Column(name = "J")
  private String jColumn;
}
