package entities;

import db.go;

public class Note
{       private int numC;
        private int numE;
	private float note;
	private String comment;
        

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void saisirNote(int c,int e, double noteE)
	{
         String sql ="INSERT INTO note VALUES ("+c+","+e+","+noteE+",'')";
         go.RunNonQuery(sql);
	}
        
}
