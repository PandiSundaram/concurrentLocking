# concurrentLocking
project about optimistic and pessimistic concurreny control


Optimistic.read or read - @Versions
Optimistic.forceincrment or write -@Version

pessimatic.read  - allows other reads to access the locked resource but wont allow to write
pessimatic.write - will not allow to read or write until releases the lock
pessimatic.forceincrement -@Version - used to increase version number

needs timout for pessimatic locking to prevent deadlock



Optimistic Lock
     - @Version - will increment the version value to 1 each and every update.
  During the racing condition if other thread updating the data by reading the value once again
if versionId has changed, database will throw the exception.

1. created employee entity and saved employee object
2. while updating employdetails with two threads, application allow us to update first thread
3. When we have entity relationship mapping if we are updating the details only on the child entity wont cretate and proble
4. When there are two update happens paralley one with parent and one with child we have to use forceincrement


