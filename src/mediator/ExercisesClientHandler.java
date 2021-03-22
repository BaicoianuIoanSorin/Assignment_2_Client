package mediator;

import com.google.gson.Gson;
import model.Exercise;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ExercisesClientHandler implements Runnable, PropertyChangeListener {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;
    private Model model;
    private PropertyChangeSupport propertyChangeSupport;

    public ExercisesClientHandler(Socket socket, Model model) throws IOException {
        this.model = model;
        gson = new Gson();
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);

    }

    @Override
    public void run() {
        ExercisePackage exercisePackage;
        try {
            exercisePackage = gson.fromJson(in.readLine(),ExercisePackage.class);
            switch (exercisePackage.getType()){
                case "add":{
                    model.addExercise(exercisePackage.getExercise());
                    break;
                }
                case "remove":{
                    try {
                        model.removeExercise(exercisePackage.getNumber());
                    }
                    catch (Exception e){
                        ExercisePackage exercisePackage1 = new ExercisePackage("remove","error: No such exercise exists");
                        out.println(exercisePackage1);
                    }
                    break;
                }
                case "edit":{
                    model.editExercise(exercisePackage.getNumber(),exercisePackage.getExercise());
                    break;
                }
                case "get":{
                    try {
                        model.getExercise(exercisePackage.getNumber());
                    }
                    catch (Exception e){
                        ExercisePackage exercisePackage1 = new ExercisePackage("get","error: No such exercise exists");
                        out.println(exercisePackage1);
                    }
                    break;
                }
                case "all":{
                    out.println(gson.toJson(model.getAllExercises()));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        socket.close();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ExercisePackage exercisePackage = new ExercisePackage(evt.getPropertyName(),(Exercise)evt.getNewValue(),(String)evt.getOldValue());
        out.println(gson.toJson(exercisePackage));
    }
}
