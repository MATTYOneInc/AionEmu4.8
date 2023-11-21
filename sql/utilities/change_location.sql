/*
when upgrading to 4.8:
Some players will have stopped character, since some maps are no longer supported by the client.
This utility is used to move players to the starting position.
At the same time I suggest to delete the table player_bind_point
*/

UPDATE `players` SET `x`='1211',`y`='1045',`z`='142',`world_id`='210010000' WHERE `race`='ELYOS';
UPDATE `players` SET `x`='571',`y`='2786',`z`='300',`world_id`='220010000' WHERE `race`='ASMODIANS';
