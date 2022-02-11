package br.ifsul.models;

import java.util.ArrayList;

public class Word {
	
	private Long id;
	private String text;
	private String pronunciation;
	private ArrayList<Phrase> phrases;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public ArrayList<Phrase> getPhrases() {
		return phrases;
	}
	public void setPhrases(ArrayList<Phrase> phrases) {
		this.phrases = phrases;
	}	
}
