package com.interview.interview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "X")
public class RecordModel {
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
  @Size(max = 9999)
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

  @Override
  public String toString() {
    return aColumn + ", "
            + bColumn + ", " + cColumn + ", "
            + dColumn  + ", " + eColumn  + ", "
            + fColumn  + ", " + gColumn  + ", "
            + hColumn  + ", " + iColumn + ", "
            + jColumn;
  }
}


