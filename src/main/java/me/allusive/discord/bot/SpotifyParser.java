package me.allusive.discord.bot;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyParser {

    public static void main(String[] args) {
        SpotifyParser parser = new SpotifyParser();
        String parsed = parser.parseSpotifyURL(
                "https://open.spotify.com/track/3NdAy34nMtNab8eGyt0Lth?si=1aeb5f6dbb344a1d");
        System.out.println(parsed);

        System.out.println(parser.spotifyAPIReq(parsed));

    }

    // Parse Spotify URI and return track ID
    public String parseSpotifyURL(String url) {
        url = url.replace("https://open.spotify.com/track/", "");
        url = url.split("\\?")[0];
        return url;
    }



    // Parse Spotify URI and return track ID
    public String spotifyAPIReq(String id) {
        final String clientId = "";
        final String clientSecret = "";

        final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId)
                .setClientSecret(clientSecret).build();
        final ClientCredentialsRequest clientCredentialsRequest =
                spotifyApi.clientCredentials().build();
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        try {
            String name = spotifyApi.getTrack(id).build().execute().getName();
            String artist = spotifyApi.getTrack(id).build().execute().getArtists()[0].getName();
            System.out.println(spotifyApi.getTrack(id).build().execute().getName());
            return name + " " + artist;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "";
    }

    public void searchYoutube(String query) {

    }

}
