package Modelo;


public class Contacto {
    
    private String id;
    private String nombre;
    private String telefono;
    private String correoElectronico;
    private String pasatiempos;
    private String areaComun;

    public Contacto() {
    }

    public Contacto(String id, String nombre, String telefono, String correoElectronico, String pasatiempos, String areaComun) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.pasatiempos = pasatiempos;
        this.areaComun = areaComun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPasatiempos() {
        return pasatiempos;
    }

    public void setPasatiempos(String pasatiempos) {
        this.pasatiempos = pasatiempos;
    }

    public String getAreaComun() {
        return areaComun;
    }

    public void setAreaComun(String areaComun) {
        this.areaComun = areaComun;
    }

    @Override
    public String toString() {
        return "Contacto{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + ", pasatiempos=" + pasatiempos + ", areaComun=" + areaComun + '}';
    }
    
}
