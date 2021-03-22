package viewmodel;

public class ViewModelState
{
  private String number;
  private boolean remove;

  public ViewModelState()
  {
    this.number = null;
    this.remove = false;
  }

  public String getNumber()
  {
    return number;
  }

  public void setNumber(String number)
  {
    this.number = number;
  }

  public void removeNumber()
  {
    this.number = null;
  }

  public boolean isRemove()
  {
    return remove;
  }

  public void setRemove(boolean remove)
  {
    this.remove = remove;
  }
}
