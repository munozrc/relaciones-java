package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import vista.RegistroVehiculo;
import modelo.Taxi;
import modelo.Vehiculo;

public class ControladorVehiculo implements ActionListener {

    RegistroVehiculo registroVehiculo; // Vista de registro de vehiculo
    ArrayList<Taxi> listaTaxis; // Array de taxis registrados

    public ControladorVehiculo() {
        // Inicializar vistas.
        registroVehiculo = new RegistroVehiculo();

        // Inicializar Eventos Vista Registro vehiculo
        registroVehiculo.btnRegistrarVehiculo.addActionListener(this);
        registroVehiculo.comboboxTipo.addActionListener(this);

        // Inicializar arrays de vehiculos
        listaTaxis = new ArrayList<>();
    }

    public void iniciarControlador() {

        // Ingresar valores taxis por defecto.
        listaTaxis.add(new Taxi("ABC123", "Mazda"));
        listaTaxis.add(new Taxi("DDD122", "Hyunday"));
        
        // Inicializar Estado Vista Registro vehiculo
        campoDeSoat(false); // Deshabilitar Campo de soat.

        // Iniciar Primera Vista
        registroVehiculo.setVisible(true);

    }

    private void imprimirInfoVehiculo(Vehiculo vehiculo) {
        System.out.println("\n:.::::::::: Vehiculo :::::::::");
        System.out.println("Nombre: " + vehiculo.getNombre());
        System.out.println("Placa: " + vehiculo.getPlaca());
        System.out.println("Color: " + vehiculo.getColor());
        System.out.println("Empresa: " + vehiculo.getEmpresa());
    }

    private void limpiarCamposVistaRegistroVehiculo() {
        this.registroVehiculo.comboboxTipo.setSelectedIndex(0);
        this.registroVehiculo.txtPlaca.setText("");
        this.registroVehiculo.txtColor.setText("");
        this.registroVehiculo.txtEmpresa.setText("");
    }

    public Taxi añadirNuevoTaxi(String placa, String empresa) {
        Taxi nuevoTaxi = new Taxi(placa, empresa);
        listaTaxis.add(nuevoTaxi);
        return nuevoTaxi;
    }

    public void campoDeSoat(boolean estado) {
        JTextField campoSoat = this.registroVehiculo.txtSoat;
        JLabel labelCampoSoat = this.registroVehiculo.LabelSoat;
        campoSoat.setVisible(estado);
        labelCampoSoat.setVisible(estado);
    }

    private boolean validarCampos(String nombre, String placa, String empresa, String color) {
        try {
            if (nombre.equalsIgnoreCase("Seleccione Tipo")) {
                throw new Error("No ingreso nombre del vehiculo");
            }
            if (placa.equalsIgnoreCase("")) {
                throw new Error("No ingreso placa del vehiculo");
            }
            if (color.equalsIgnoreCase("")) {
                throw new Error("No ingreso color del vehiculo");
            }
            if (empresa.equalsIgnoreCase("")) {
                throw new Error("No ingreso empresa del vehiculo");
            }
            return true; // Todos los campos son validos
        } catch (Error errores) {
            String mensajeDeError = errores.getMessage();
            JOptionPane.showMessageDialog(null, mensajeDeError);
            return false; // Algunos de campos estan vacios o no son validos.<
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.registroVehiculo.btnRegistrarVehiculo) {
            String placa = this.registroVehiculo.txtPlaca.getText();
            String empresa = this.registroVehiculo.txtEmpresa.getText();
            String color = this.registroVehiculo.txtColor.getText();
            String nombre = this.registroVehiculo.comboboxTipo.getSelectedItem().toString();

            // Validar campos enviados desde la vista
            if (!validarCampos(nombre, placa, empresa, color)) {
                return;
            }

            // Clasificar el tipo de vehiculo
            if (nombre.equalsIgnoreCase("Taxi")) {
                Taxi nuevoTaxi = añadirNuevoTaxi(placa, empresa);
                imprimirInfoVehiculo(nuevoTaxi);
                limpiarCamposVistaRegistroVehiculo();
                JOptionPane.showMessageDialog(null, "Taxi Registrado con exito");
            }
        }

        // Evento del comboBox vista Registro vehiculo
        if (e.getSource() == this.registroVehiculo.comboboxTipo) {

            JComboBox<String> tipo = this.registroVehiculo.comboboxTipo;
            JTextField campoColor = this.registroVehiculo.txtColor;

            if (tipo.getSelectedItem().equals("Taxi")) {
                campoColor.setText("Amarillo");
                campoColor.setEnabled(false);
                
                // Deshabilitar Campo de soat.
                campoDeSoat(false);
            } else if (tipo.getSelectedItem().equals("Moto")) {
                campoColor.setEnabled(true);

                // Habilitar Campo de soat.
                campoDeSoat(true);
            } else {
                campoColor.setText("");
                campoColor.setEnabled(true);
                
                // Habilitar Campo de soat.
                campoDeSoat(false);
            }
        }
    }
}
