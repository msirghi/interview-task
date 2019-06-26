package com.interview.interview.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "X")
@Getter
@Setter
@ToString
public class TaskModel {
  @Column(name = "A")
  private String aColumn;

  @Column(name = "B")
  private String bColumn;

  @Column(name = "C")
  private String cColumn;

  @Column(name = "D")
  private String dColumn;

  @Column(name = "E")
  private String eColumn;

  @Column(name = "F")
  private String fColumn;

  @Column(name = "G")
  private String gColumn;

  @Column(name = "H")
  private boolean hColumn;

  @Column(name = "I")
  private boolean iColumn;

  @Column(name = "J")
  private String jColumn;
}
