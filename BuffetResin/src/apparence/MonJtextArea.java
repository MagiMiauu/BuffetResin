package apparence;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import javax.swing.JTextArea;

public class MonJtextArea extends JTextArea 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String textHolder;
	/**
	 * @param textHolder
	 */
	public MonJtextArea(String textHolder) 
	{
		super(1,25);
		this.textHolder = textHolder;
		setForeground(Color.darkGray);
		setText(textHolder);
		this.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				if(txt().equals("")) 
				{
					setForeground(Color.darkGray);
					txt(textHolder);
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				// TODO Auto-generated method stub
				if(txt().equals(textHolder)) 
				{
					setForeground(Color.black);
					txt("");
				}
			}
		});
		
	}
	
	/**
	 * @return
	 */
	private String txt() 
	{
		return super.getText();
	}
	
	/**
	 * @param s
	 */
	private void txt(String s) 
	{
		super.setText(s);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#getText()
	 */
	public String getText() 
	{
		if (super.getText().equals(textHolder) ) 
		{
			return "";
		}
		else 
		{
			return super.getText();
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setText(String donneeText) 
	{
		setForeground(Color.BLACK);
		if(donneeText.equals("")) 
		{
			setForeground(Color.darkGray);			
			super.setText(textHolder);
		}else 
		{
			super.setText(donneeText);	
		}
	}
}
