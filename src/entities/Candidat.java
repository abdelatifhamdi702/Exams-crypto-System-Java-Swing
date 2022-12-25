package entities;

import java.util.Date;
import javax.swing.JTable;

public class Candidat
{
	private int numero;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String univOrigine;
	private String code;

	public Candidat() {}
	public Candidat(int numero, String nom, String prenom, Date dateNaissance, String univOrigine)
	{
		this.setNumero(numero);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateNaissance(dateNaissance);
		this.setUnivOrigine(univOrigine);
		this.generateCode();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getUnivOrigine() {
		return univOrigine;
	}

	public void setUnivOrigine(String univOrigine) {
		this.univOrigine = univOrigine;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/* CLASSE Functions */

	public void saisirNote()
	{

	}

	protected String generateCode()
	{
		int numeroOrigine = this.getNumero();
		String tempNumero = String.valueOf(numeroOrigine);

		if (tempNumero.length() % 5 == 0)
		{
			System.out.println(tempNumero.length() % 5 +  "Yes");
		}
		else
		{
			System.out.println(tempNumero.length() % 5 +  "No");
			int tempCode = 0;
			int n = tempNumero.length() % 5;
			tempCode = ((int) Math.round(Math.pow( 10, (n+1)*5 ))) - numeroOrigine;
			System.out.println(tempCode);
		}

		return "EXAMPLE-GENRATED-CODE";
	}
public void getAllRows(JTable table) {
db.go.fillToJTableWithTableName("candidat", table);    }
	public void exportList()
	{

	}

	public void update()
	{

	}

}
