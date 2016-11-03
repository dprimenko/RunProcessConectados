import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class RunProcessConectados {
	
	private static void MostrarSalidaProceso (Process proceso) {
		try {
			int retorno = proceso.waitFor();
			System.out.println("La ejecución del proceso devuelve: " + retorno);
			InputStreamReader lector = 
					new InputStreamReader(proceso.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(lector);
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		if (args.length <= 0) {
			System.err.println("Debes dar un ejecutable");
			System.exit(-1);
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		
		// Fuerza la redireccion del mensaje de error a la salida estándar
		pb.redirectErrorStream(true);
		
		try {
			Process proceso = pb.start();
			MostrarSalidaProceso(proceso);
			System.exit(0);
		} catch (IOException ex) {
			System.err.println("Error de E/S");
			System.exit(-1);
		}
	}
}
