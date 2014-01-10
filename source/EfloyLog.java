//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//

import java.awt.*;
import java.util.Date;

/*
 Based on ZDebugWindow 1.5   96/12/6   Andre Pinheiro (l41325@alfa.ist.utl.pt)
 Copyright (c) 1996, JavaZine - Online magazine about Java and netprogramming
 (http://camoes.rnl.ist.utl.pt/~adlp/JavaZine/index.html)
 All Rights Reserved.
*/


public class EfloyLog extends Frame
{
  TextArea ta;
  Date StartDate, EndDate;


  EfloyLog(String title)
  {
    super(title);

    ta = new TextArea(50,100);
    ta.setText("");
    setLayout(new BorderLayout());
    add("Center", ta);

    this.pack();
    //show();
  }


  public boolean handleEvent(Event ev)
  {
    if (ev.id == Event.WINDOW_DESTROY)
    {
      hide();
	  //toBack();
      return true;
    }
    else
      return super.handleEvent(ev);
  } 


  public void start()
  {
	StartDate = new Date();
	if (Efloys.InLog)
	    ta.appendText("Session Started at: " + StartDate.toString()+"\n\n");
  }


  public void end()
  {
	EndDate = new Date();
	if (Efloys.InLog)
	    ta.appendText("End at: " + EndDate.toString());
  }


  public void showMsg(String msg)
  {
	if (Efloys.InLog)
		ta.appendText(msg + '\n');
  }


  public void showMsg(Object obj)
  {
	if (Efloys.InLog)
	    ta.appendText(String.valueOf(obj) + '\n');
  }


  public void showMsg(int n)
  {
	if (Efloys.InLog)
	    ta.appendText(String.valueOf(n) + '\n');
  }


  public void clear()
  {
	if (Efloys.InLog)
		ta.setText("");
  }


  public void clear(String msg)
  {
	if (Efloys.InLog)
	    ta.setText(msg);
  }
}
