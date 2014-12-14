package com.example.e4.rcp.todo.parts;

import java.text.DateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;






import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class ReceiverView {
  private TableViewer viewer;
 
  @PostConstruct
  public void init(final Composite parent) {
    parent.setLayout(new FillLayout());
    viewer = new TableViewer(parent);
    viewer.getTable().setHeaderVisible(true);
    viewer.getTable().setLinesVisible(true);
    viewer.setLabelProvider(new ColumnLabelProvider() {
      @Override
      public String getText(Object element) {
        return (String) element;
      }
    });
  }
 
  @Inject
  @Optional
  void eventReceived(@UIEventTopic("viewcommunication/*") String date) {
      System.out.println(date.toString());
	  viewer.add(date);
  }
  

  @Focus
  void focus() {
    viewer.getTable().setFocus();
  }
}