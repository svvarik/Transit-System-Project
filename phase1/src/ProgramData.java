import java.io.*;
import java.util.ArrayList;

public class ProgramData {

    ArrayList<String> data = new ArrayList<>();

    /**
     * Initializes a file to save data to, when program is run. Creates a new
     * 'save' file if it does not exist.
     *
     * PRECONDITION: Save file does not exist
     * prior to starting application for first time
     *
     */
    public void initDataFiles() {
        File newFile1 = new File("programData.txt");
        if (!newFile1.exists()) {
            try{
                new FileWriter("programData.txt");
            }
            catch (IOException exception){
                System.out.println(exception);
            }
        }
    }


    public void readData(main model) {
        try{
            FileReader fileReader1 = new FileReader(".programData.txt");
            BufferedReader fileReader = new BufferedReader(fileReader1);
            String newLine;

            while((newLine = fileReader.readLine()) != null) {
                data.add(newLine);
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeHistory(main model) {
        try {
            OutputStreamWriter writer = new FileWriter(".history.txt");
            BufferedWriter buffer = new BufferedWriter(writer);

            for(int i = 0; i < data.size(); i +=1){
                if(data.get(i) != null){
                    buffer.write(data.get(i));
                    buffer.newLine();
                }
            }
            buffer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // TODO: Add diff types of transactional data to txt file.


}

