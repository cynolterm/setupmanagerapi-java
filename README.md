# setupmanagerapi-java

- How to run the project:

1. Have postgres db running on your system
2. Change the path and authentication to postgres db in the application.yaml
3. Import the project into IntelliJ and hit run, or simply run it with the maven command

# Capabilities:
## Users
- Not protected, available methods:

1. GET getUserById -> returns a user if there is one defined by the given Id
2. GET getUsers -> takes limit and sort params, return a list of users with respect to the params
3. POST register -> creates a new user, with the role of USER

## Cars
- Protected, get method for logged in user, create / put / delete for ADMIN

1. GET getCarById -> return a car object by Id, if it exists
2. GET getAllCars -> returns all the cars
3. POST createCar -> creates a Car entity
4. PUT updateCar -> updates a car based on Id, if it exists
5. DELETE deleteCar -> removes a car from the DB if it exists

## Tracks
- Protected, get method for logged in user, create / put / delete for ADMIN

1. GET getTrackById -> return a track object by Id, if it exists
2. GET getAllTracks -> returns all the tracks
3. POST createTrack -> creates a track entity
4. PUT updateTrack -> updates a track based on Id, if it exists
5. DELETE deleteTrack -> removes a track from the DB if it exists

## Teams
- Protected for logged in users, only users associated to teams can perform actions to it

1. GET getTeamById -> return Team by Id if it exists
2. GET getAllTeams -> return all existing teams
3. DELETE deleteTeamById -> delete team from DB by id, if it exists
4. POST createTeam -> creates a new team
5. PUT updateTeamById -> updates the team, if the user is part of the team
6. POST createSetup -> creates a setup for a team, if the user is part of the team
7. POST updateSetup -> creates a new setup variant for a setup for a team if the user is part of the team
8. GET getAllSetupsByTeamId -> if the user is part of the team, returns all the setups for the team
9. GET getSetupDetailsByTeamIdAndSetupId ->returns the details for a setup if the user is part of the team