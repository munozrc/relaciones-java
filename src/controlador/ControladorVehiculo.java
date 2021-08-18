
package controlador;

import vista.RegistroVehiculo;

public class ControladorVehiculo {

    RegistroVehiculo registroVehiculo;
    
    public ControladorVehiculo() {
        registroVehiculo = new RegistroVehiculo();
    }
    
    public void iniciarControlador() {
        // Iniciar Primera Vista
        registroVehiculo.setVisible(true);
    }
}
