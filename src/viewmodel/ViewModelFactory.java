package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private ManageExerciseViewModel manageExerciseViewModel;
  private ListExerciseViewModel listExerciseViewModel;

  public ViewModelFactory(Model model)
  {
    ViewModelState viewModelState = new ViewModelState();
    manageExerciseViewModel = new ManageExerciseViewModel(model, viewModelState);
    listExerciseViewModel = new ListExerciseViewModel(model, viewModelState);
  }

  public ManageExerciseViewModel getManageExerciseViewModel()
  {
    return manageExerciseViewModel;
  }

  public ListExerciseViewModel getListExerciseViewModel()
  {
    return listExerciseViewModel;
  }
}
