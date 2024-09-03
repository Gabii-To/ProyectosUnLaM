package ar.edu.unlam.pb1.dominio;

public class Atencion {

	private long id;
	private Paciente paciente;
	private String motivoConsulta;
	private String diagnostico;
	private double valor;

	public Atencion(long id, Paciente paciente, String motivoConsulta, String diagnostico, double valor) {
		this.id = id;
		this.paciente = paciente;
		this.motivoConsulta = motivoConsulta;
		this.diagnostico = diagnostico;
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Atencion [id=" + id + ", paciente=" + paciente + ", motivoConsulta=" + motivoConsulta + ", diagnostico="
				+ diagnostico + ", valor=" + valor + "]";
	}

}
