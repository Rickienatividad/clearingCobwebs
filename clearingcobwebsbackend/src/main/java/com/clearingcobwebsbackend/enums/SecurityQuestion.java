package com.clearingcobwebsbackend.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SecurityQuestion {
  PET("What is your favorite pet's name?"),
  VACATION("Where has your favorite vacation spot been so far?"),
  TEACHER("What was the name of your favorite teacher?"),
  ATHLETE("Who is your favorite athlete?"),
  CHARACTER("Who is your favorite fictional character?");

  private final String question;

  public String getQuestion() {
    return question;
  }
}
