# ZTerm
ZTerm allows you to create a GUI application using System.out.println-like statements.

### Q: How do I install ZTerm?

A: Refer to Lecture 1 recording from the 13:59 point on the clock in the shared screen:

https://rmit.instructure.com/courses/67194/discussion_topics/802227

 

###  Q: What are ZTerm methods and how do I use the documentation?

A: More examples of how to use GTerm methods and documentation have been shown in Tutorial 1 recording (same link as above). More examples will be given over the coming weeks also.

 

###  Q: What's this forum?

A: Use this forum for all questions relating to zTerm





### Klemens Chung

Hello,

I was wondering if this command:" zt.addButton(); " to be explained how it works?

### Zayan Zijesinghe

Hi Klemens,

Please note that you must not use this for A1 because it requires the creation of methods. For your future understanding, here's a simple program that does the job.

public class ButtonDemo{
   private ZTerm zt;

   public ButtonDemo(){
      this.zt=new ZTerm(600, 400);
      this.zt.addButton("Say something",this,"sayHello");
   }

   public void sayHello(){
      this.gt.showMessageDialog("Hello!", "Hello!");
   }

   public static void main(String[] args){
      ButtonDemo bdObj=new ButtonDemo();
   }
}
 

Remember to come to pracs and I can give you more help on such features.

Regards,

Zayan



