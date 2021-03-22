package mediator;

import model.Exercise;

import java.util.Objects;

public class ExercisePackage
{
  private String type;
  private Exercise exercise;
  private String number;
  private String error;

  public ExercisePackage(String type, Exercise exercise, String number)
  {
    this.type = type;
    this.exercise = exercise;
    this.number = number;
    this.error = null;
  }

  public ExercisePackage(String type, String error)
  {
    this.type = type;
    this.exercise = null;
    this.number = null;
    this.error = error;
  }

  public Exercise getExercise()
  {
    return exercise;
  }

  public String getNumber()
  {
    return number;
  }

  public String getType()
  {
    return type;
  }

  public String getError()
  {
    return error;
  }

  @Override public boolean equals(Object o)
  {
    if (!(o instanceof ExercisePackage))
      return false;
    ExercisePackage other = (ExercisePackage) o;
    return Objects.equals(type, other.type) && Objects
        .equals(exercise, other.exercise) && Objects
        .equals(number, other.number);
  }

  @Override public String toString()
  {
    return "Package: " + type + ", " + exercise + ", " + number;
  }

}
