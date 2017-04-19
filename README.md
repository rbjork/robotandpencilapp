This code was created for purposes for evaluation of my android code skills.

Instructions 
Let’s say say you have instances of class Comment that need to get saved somehow, via a Remote Store
of some type.

	1.	Please provide draft code, pseudo­code or a clear description that indicates how you would save
      these Comment objects to a remote store is such a fashion as to allow:

	     a.	The Remote Store to be changed at some unknown point in the future, for example, from Parse
          to a RESTful server, minimizing impact on the Android code ­ particularly avoiding direct changes
          to the Comment class itself. (Don’t worry about the API interface to the datastore unless that is
          key to your solution.)  

	     b.	The design pattern(s) adopted and implemented for Comment to be leveraged, minimizing code
          duplication, to allow other objects (e.g. a new class called Annotation ) to be saved similarly.  

		Key to our interest in this problem are the Android design patterns and language features that
    you would use to accomplish these two goals efficiently and cleanly, maximizing Object Oriented
    reusability and flexibility while avoiding over­ designed code.  

	2.	Make any assumptions you would like, just make sure that you clearly state those assumptions.
      For example, don’t worry about the low level details like networking code or asynchrony, focus
      instead on the patterns that allow (a) and (b) to be accomplished.  


First impression: Since we have a collections of comments and annotations as well as other future types not foreseen, the method for storage should handle multiple object types. Because of conversion times to amorphous strings and possible upload time, saving data should be done off the main UI thread.

There are four general approaches to perform saving outside the Activity UI thread.  They are: one, simply instantiate a runnable inside a Thread class instance;  two, use AsyncTask or Volley owned by the Activity with the UI that receives the user intention of saving;  or three, use a private Service;  or four, use a content provider to handle saving and retrieval.  

The First approach could work but since this is a fairly generic task, this may be making us do more work than needed. And if the activity goes away will loose the data.
The second approach would be the least work but it has one drawback - if the user closes the app or the activity hosting Volley or AsyncTask will terminate, resulting in termination of the saving task.
The third is to use a 'IntentService' which runs in a separate thread and will continue even it the activity stops or is destroyed by the OS.
The fourth approach is overkill, unless its known that other apps will need to save similar or identical objects(comments, annotations or derived object of either)

I have chosen to use an Intent Service with the comments and annotations saved to a model singleton.  Note the IntentService run on their own worker thread off the main thread.  We will use LocalBroadManager to broadcast message and receivers for service to activity communication.

The somewhat ambiguous part of the test is what assumptions can be made about the comment or annotation instances:  are they serializable, are they POJO annotated objects.  Also what kinds of structure do we assume the remote data store have. Is it a relational database with a table with columns to store attribute of the objects we’re storing.  For purposes of this assignment we’ll assume that the client doesn’t need to know, how the server deserializes the submitted objects are of no concern to the client android app and that we want to create a loose coupling between the data objects and the views and controllers.  For this test I’ll simply assume remote storage can accept a JSON string in all cases.

To make the client independent of changes to comment or annotation we will make use of interfaces  For each kind of object that we might want to store we will have a helper class, all of which will implement a common interface specifying methods to support serializing and deserializing the object( we won’t assume the data classes themselves support this directly or the interface the helper classes implements). 

This app will compile, install and launch.  But it isn't actually functional since no backend is connected and the view does not support creation or rendering of comments or annotations.  The code here simply demonstrates how the spec can be implemented.  To make this fully functional would require more than the couple hours expected to be used for this assignment.

Late additions include utilization of deserializing library fasterxml and google analytics for android.

Ron
