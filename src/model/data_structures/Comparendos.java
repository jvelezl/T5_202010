package model.data_structures;


import java.util.Date;

public class Comparendos implements Comparable<Comparendos> {
	private int objectId;
	private Date fecha_hora;
	private String des_infrac;
	private String medio_dete;
	private String clase_vehi;
	private String tipo_servi;
	private String infraccion;
	private String localidad;

	private double latitud;
	private double longitud;
	
	public Comparendos(int objeId, Date fecha, String descripcion, String detencion, String claseVeh, String tipoSer, String codInfraccion, String localidadP, double lonP, double latP)
	{
		objectId = objeId;
		fecha_hora = fecha;
		des_infrac = descripcion;
		medio_dete = detencion;
		clase_vehi = claseVeh;
		tipo_servi = tipoSer;
		infraccion = codInfraccion;
		localidad = localidadP;
		longitud = lonP;
		latitud = latP;
	}
	
	@Override
	public String toString() {
		return "Comparendo [OBJECTID=" + objectId + ", FECHA_HORA=" + fecha_hora + ", DES_INFRAC=" + des_infrac
				+ ", MEDIO_DETE=" + medio_dete + ", CLASE_VEHI=" + clase_vehi + ", TIPO_SERVI=" + tipo_servi
				+ ", INFRACCION=" + infraccion + ", LOCALIDAD=" + localidad + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}
	
	public int darId()
	{
		return objectId;
	}
	public String darLocalidad()
	{
		return localidad;
	}

	public Date darFecha()
	{
		return fecha_hora;
	}
	public String darInfraccion(){
		return infraccion;
	}
	public String darClaseVehi()
	{
		return clase_vehi;
	}
	public String darTipo()
	{
		return tipo_servi;
	}
	public int compareTo(Comparendos o) {
		int comparador=-2;
		if(objectId < o.darId())
			comparador=-1;
		else if(objectId == o.darId())
			comparador=0;
		else if(objectId> o.darId())
			comparador=1;
		return comparador;
	}
	public int compareToDate(Comparendos o)
	{
		int comparador=-2;
		if(Integer.parseInt(fecha_hora.toString() )< Integer.parseInt(o.darFecha().toString()))
			comparador=-1;
		else if(fecha_hora == o.darFecha())
			comparador=0;
		else if(Integer.parseInt(fecha_hora.toString() )>Integer.parseInt(o.darFecha().toString()))
			comparador=1;
		return comparador;
	}
	public int compareToInfraccion(Comparendos o)
	{
		int comparador=-2;
		if(Integer.parseInt(infraccion)< Integer.parseInt(o.infraccion))
			comparador=-1;
		else if(Integer.parseInt(infraccion)== Integer.parseInt(o.infraccion))
			comparador=0;
		else if(Integer.parseInt(infraccion)> Integer.parseInt(o.infraccion))
			comparador=1;
		return comparador;
	}
	
}


