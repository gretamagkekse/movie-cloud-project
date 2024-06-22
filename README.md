# Project Team Yellow

## Running Docker Compose 
to start the database, you have to run the docker container.
 - first, see if the container is already running by typing in the terminal 'docker ps'
 - then, use the command 'docker compose up --detach' to create and run a docker container



## Pull changes from main to your dev branch
```
git checkout [local_branch_name] //wenn man noch nicht auf dem richtigen lokalen Branch ist
git fetch origin       
git merge origin/main
```

## Create new feature branch
```
git checkout -b [name_of_feature_branch] //erstellt neuen lokalen Branch
git push --set-upstream origin [name_of_feature_branch] //erstellt Verbindung zu remote Branch

```

## Add your local changes to remote branch
```
git checkout [local_branch_name] //wenn man noch nicht auf dem richtigen lokalen Branch ist
git add .
git commit -m '[message]'
git push
```

## Merge remote branch into main
1. On the left sidebar, select Search or go to and find your project.
2. Select Code > Merge requests.
3. In the upper-right corner, select New merge request.
4. Select a source and target branch, then select Compare branches and continue.
5. Complete the fields on the New merge request page, then select Create merge request

