Brandon Pauly
Shared Editor Project
CSC 376

This is my second attempt at a shared document editor.    I struggled quite a bit trying to get my actors doing what they needed to do and wanted a more "tangible" project to give you.  So here it is.  It works, as I hope you have seen in the video.  Multiple clients can edit a text pane, edit it concurrently, and save from it.  If you would like to see it work for yourself, it is a maven project that can be built with dependencies in netbeans.  From there, you can just run the TextServer class and run as many ClientUI classes as you want clients.  The most I have tested with is three, however.

The first class is the ClientUI class.  This class handles the GUI stuff with a text box and a very small menu bar.  The menu bar allows you to save the text in the text box to a .txt file.  The other option is to exit the GUI.  Whenever a character key is pressed, the ClientUI class instantiates a new CharObject, which is a small class that just notes the position of the change in the text box, and the character typed.  This new CharObject is sent over the output stream for the system to process.  Whenever backspace is used, the ClientUI instantiates a new DeleteObject, which is another small class that just notes the position of the change in the text box.  This is also sent over the network when this the backspace is pressed and a character is deleted.

The next class would be the TextServer.  It is a fairly simple class.  It starts a broadcaster, which manages sending out of modifications to the document.  Then the TextServer simply listens for new incoming clients to edit the document, and places them into a collection of clients for the broadcaster to utilize.  The Client class is essentially a class representing the client on the server side.  Like a connection object.

The next class is the Broadcaster.  The broadcaster has an arrayList of all of the Client instances, and a concurrent queue for modifications.  The broadcaster then loops looking for modifications in the queue.  The queue holds DeleteObjects and CharObjects.  Whenever a change is encountered, the Broadcaster loops throught the Clients, and tells each Client who wasn't the client who initiated the modification, that the modification occurred.

The next class is Client.  Like I mentioned earlier, Client represents a connection.  One thread per client.  The Client listens for any modifications from its input stream that its ClientUI sent out.  When one is received, it goes into the Broadcaster's queue.  The Client class also has insert and delete methods that the Broadcaster calls from it's loop.
When these are called, the CharObject or DeleteObject that is coming from the Broadcaster's queue is sent over the output stream to the appropriate client.

As we head towards bringing it back around, we end up at the ModListener class.  The ModListener handles listening on the client side for objects over the network.  When an object comes in, the listener simply checks the class of the object (if it is a DeleteObject or a CharObject) and calls ClientUI's appropriate method (insert/delete).

So now we are back at the ClientUI, where delete or insert is called.  Here the GUI just inserts in the proper position, or deletes the character from the proper position.