https://github.com/abesk/my-maps
-------------------------------------------
How to run aplication:
1) Start server
2) Deploy the .war file
3) Run the app on <host>/my-maps address


-------------------------------------------

How to operate:
Registrate (trivial)
Browse maps, create your own map, insert some point of interest by clicking on the map

Unregistered users cannot create maps, they can only see these whose authors permitted it for unregistered users by selecting "View permission".

Before creating your map, you have to create a "View" of the map by clicking "Create view" and select it.
Then you can reuse it in all your newly created maps.

Also you can log in as admin by login "admin" and password "admin" and manage logged users.

-------------------------------------------

What couldn't be done:
OpenShift deploy - documented by three .png snapshopts.

Clustering - because of using Seam 3 for authentication and authorization
The class org.picketlink.idm.impl.api.PasswordCredential is not serializable and Infinispan wasn't able to replicate these entities.
We couldn't find any way, how to workaround it.



