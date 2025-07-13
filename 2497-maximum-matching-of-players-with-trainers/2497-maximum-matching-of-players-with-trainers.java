class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        
        int playerIndex = 0;
        int trainerIndex = 0;
        int matchings = 0;
        
        while (playerIndex < players.length && trainerIndex < trainers.length) {
            if (players[playerIndex] <= trainers[trainerIndex]) {
                matchings++;
                playerIndex++;
                trainerIndex++;
            } else {
                trainerIndex++;
            }
        }       
        return matchings;
    }
}