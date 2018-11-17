import java.io.FileWriter;

public class test {

	public static void main(String[] args) {
		
		        try {
		            FileWriter fw = new FileWriter("/home/amneiht/sdf/testout.txt");
		            fw.write("Welcome to java.");
		            fw.close();
		        } catch (Exception e) {
		            System.out.println(e);
		        }
		        System.out.println("Success...");
		    }
		

	}

