//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//

/*
	This class is based on Kul Bhatt's ScrollablePanel class.
    @(#)ScrollablePanel.java         0.1 96/10/29 Kul Bhatt
    k.bhatt@ieee.org 
*/

import java.awt.*;



public class EfloyScrollPanel extends Panel implements LayoutManager
{
  Panel viewPort;
  Scrollbar vbar;
  Scrollbar hbar;

  Component scrolledComponent;
  String bars;
  
  public EfloyScrollPanel(Component scrolledComponent, String barsType)
  {
    this.scrolledComponent = scrolledComponent;
	bars = barsType;
    viewPort = new Panel();
    hbar = new Scrollbar(Scrollbar.HORIZONTAL);
    vbar = new Scrollbar(Scrollbar.VERTICAL);
    setLayout(this);

	add(viewPort);

	if (bars.equals("V"))
		add(vbar);
	else
	if (bars.equals("H"))
		add(hbar);
	else
	if (bars.equals("HV")) {
		add(vbar);
		add(hbar);
	}

    //viewPort.setLayout(new LazyLayout());
	viewPort.add(scrolledComponent);
  }

  public  void addLayoutComponent(String  name,  Component  comp)
  {
  }
  
      
  public  void layoutContainer(Container  parent)
  {
    Insets insets = parent.insets();
    Dimension parentSize = parent.size();
    int top = insets.top;
    int left = insets.left;
    int bottom = parentSize.height - insets.bottom;
    int right = parentSize.width - insets.right;

    Dimension pv = vbar.preferredSize();
    Dimension ph = hbar.preferredSize();
    
    vbar.reshape(right - pv.width, top, pv.width, bottom - top - ph.height);
    hbar.reshape(left, bottom - ph.height,  right - left - pv.width,
		 ph.height);
    viewPort.reshape(left, top, right -  vbar.size().width, 
		     bottom- hbar.size().height);

    manageScrollbars();
 
  }
  
  public  Dimension minimumLayoutSize(Container  parent)
  {
    Dimension d = new Dimension(scrolledComponent.minimumSize());
    d.height +=  hbar.preferredSize().height;
    d.width +=  vbar.preferredSize().width;
    return d;    
  }
  
  public  Dimension preferredLayoutSize(Container  parent)
  {
    Dimension d = new Dimension(scrolledComponent.preferredSize());
    d.height +=  hbar.preferredSize().height;
    d.width +=  vbar.preferredSize().width;
    return d; 

  }
  
  public  void removeLayoutComponent(Component  comp)
  {
  }

  private void manageScrollbars()
  {
    Dimension v = vbar.size();
    Dimension h = hbar.size();
    Dimension sc = scrolledComponent.size();
    
    vbar.setValues(0, v.height, 0, sc.height - v.height);
    hbar.setValues(0, h.width, 0, sc.width - h.width);
    hbar.setLineIncrement( sc.width/10);
    hbar.setPageIncrement( sc.width);
    vbar.setLineIncrement( sc.height/10);
    vbar.setPageIncrement( sc.height);
  }


  
  public Dimension preferredSize()
  {
    Dimension d = new Dimension(scrolledComponent.preferredSize());
    d.height +=  hbar.preferredSize().height;
    d.width +=  vbar.preferredSize().width;
    return d;
  }


  public Dimension minimumSize()
  {
    return preferredSize();
  }

  public boolean handleEvent(Event event)
  {
    switch (event.id){
    case Event.SCROLL_LINE_UP:
    case Event.SCROLL_LINE_DOWN:
    case Event.SCROLL_PAGE_UP:
    case Event.SCROLL_PAGE_DOWN:
    case Event.SCROLL_ABSOLUTE:
      scroll();
    }
    return super.handleEvent(event);
  }


  public void scroll()
  {
	if (bars.equals("HV"))
		scrolledComponent.move(-hbar.getValue(), -vbar.getValue());
	else
	if (bars.equals("V"))
		scrolledComponent.move(0, -vbar.getValue());
	else
	if (bars.equals("H"))
		scrolledComponent.move(-hbar.getValue(),0);
  }

}

  
    





