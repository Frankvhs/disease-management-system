package utils;

import javax.swing.JTextField;

public class Validacion {
	
	// M�todo para validar n�meros positivos
    public static int validarEdad(JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim();
        
        // Validar que no est� vac�o
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vac�o");
        }
        
        try {
            int valor = Integer.parseInt(texto);
            
            // Validar que sea mayor que cero
            if (valor <= 0 || valor > 120) {
                throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe tomar un valor en el rango de 1 a 120.");
            }
            
            return valor;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El campo '" + nombreCampo + "' debe ser un n�mero entero v�lido");
        }
    }
    
    //M�todo para validar sexo de una persona
    public static String validarSexoPersona(JTextField campo, String nombreCampo){
    	String texto = campo.getText().trim();
    	
    	// Validar que no est� vac�o
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vac�o");
        }
        
        //Para que sea siempre valida la palabra sin importar si hay letras en mayusculas o minusculas
        String sexoNormalizado = texto.toLowerCase();
        
        //Validar que sea masculino o femenino 
        if (!sexoNormalizado.equals("masculino") && !sexoNormalizado.equals("femenino")){
        	throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser masculino o femenino.");
        }
        
        return texto;
    }
    
    //M�todo para validar id de una persona
    public static String validarIdPersona(JTextField campo, String nombreCampo){
    	String texto = campo.getText().trim();
    	
    	// Validar que no est� vac�o
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vac�o");
        }
        
        //Validar que solo sean digitos 11 digitos 
        //[0-9]Es una expresion regular que representa cualquier d�gito del 0 al 9
        // y {11} al final indica que solo puede ser de 11 digitos
        if (!texto.matches("[0-9]{11}")){
        	throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe contener 11 d�gitos(0 al 9).");
        }
    	
        return texto;
    }
    
    public static String validarNombre(JTextField campo, String nombreCampo){
    	String texto = campo.getText().trim();

    	//Validar que empiece con letra mayuscula
    	//seguido de al menos una letra minuscula 
    	//^[A-Z] - significa que el primer caracter tendra que ser una letra mayuscula de la A a la Z
    	if(!texto.matches("^[A-Z�������][a-z�������]+$")){
    		throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe empezar con letra may�scula y las restantes min�sculas.");
    	}
    	return texto;
    }
    
    
  //Retornar un boolean en relacion con lo que el usuario ingreso en diagnostico
    public static boolean volverBooleanADiagnostico(JTextField campo, String nombreCampo){
    	
    	boolean isDiagnosticado = false;
    	
    		String texto = campo.getText().trim();
    	
    		//Para que sea siempre valida la palabra sin importar si hay letras en mayusculas o minusculas
    		String diagnosticoNormalizado = texto.toLowerCase();
    	
    		if (diagnosticoNormalizado.equals("s�") || diagnosticoNormalizado.equals("si")){
    			isDiagnosticado = true;
    		}else if (diagnosticoNormalizado.equals("no")){
    			isDiagnosticado = false;
    		}
    		return isDiagnosticado;
    		
    }

}
