package io.hellsing.data;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;


@Component
public class User {
	@NotNull
	@Email(message="Invalid email")
	private String email;
	@Size(min=6, message="Password must be 6 or more characters")
	private String password;
	@Size(min=2, max=35)
	private String firstName;
	@Size(min=2, max=35)
	private String lastName;
	
	
	private List<Memo> memos = new ArrayList<>();
	
	public User() { 
		memos.add(new Memo("Hello", "This is your first memo!"));
	}
	
	public User(String e, String fn, String ln, String p) {
		this.email = e;
		this.firstName = fn;
		this.lastName = ln;
		this.password = p;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				 + "]";
	}

	public List<Memo> getMemos() {
		return memos;
	}

	public void setMemos(List<Memo> memos) {
		this.memos = memos;
	}
	
	public void addMemo(Memo memo){
		memos.add(memo);
	}
	
	public String getMemosInSavableFormat(){
		StringBuilder br = new StringBuilder();
		
		for (Memo memo : memos) {
			if(memos.indexOf(memo) == memos.size()-1){
				br.append(memo.getName() + "|"+ memo.getContent());
			} else{
			br.append(memo.getName() + "|" +  memo.getContent() + "|");
			}
		}
		return br.toString();
	}
	
	
}
