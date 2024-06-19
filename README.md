# AudioPlayer_MM

This application shows a basic example of an Android audio player and demonstrates how the Media Player class works.

When starting the application, we see a simple layout with buttons for playback functionality to play and pause a song and to go back and forward in a set time increment (I did 10 seconds). There is also text views for showing the song duration, the songs time remaining, and a scroll bar to visualize where the time is at in the song.

![1](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/0b1c2229-a92a-4ccf-9a53-6153cb749085)


When I press the play button ( > ) we see a message that the song is playing, and the time remaining and song duration text views change accordingly. The time remaining will continually update along with the scroll bar. 
![2-play](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/f7e8c988-ef5c-4c3a-add0-e6c1002ff889)


When pressing the pause button ( || ) we see a message that the song is paused. The scroll bar and time remaining textview also pause. Also, whenever the pause button is pressed, it is grayed out and unclickable until the play button is clicked. It's the same logic but vice versa for the play button.

![3-pause](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/ca93c444-c03b-431e-812d-30f988da2851)


Clicking the forward button ( >> ) lets us go forward 10 seconds and displays a message. It doesn't matter whether the song is played or paused.

![4-forward10](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/a36089f7-6d5c-4d97-bcd3-8d36e8063839)


It's the same for the back button ( << ) except it lets us go back 10 seconds.
![5-back10](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/65b18b8b-5c0b-43a4-bcea-154283462888)


In the next two pictures, we can see that if we are at the beginning or end of the song and it can't be forwarded or reversed, it displays a message. 

![errFwd10](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/2bd608c4-3745-4630-862c-73e2c9e892a3)
![errBack10](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/f6872e57-2ed1-4384-8b36-28e1d3de8bb6)


This is the project structure. As you can see we have a raw folder with our mp3 song. It should be noted that the song and its name are hardcoded into the application. The app just shows the playback functionality of the MediaPlayer class and not the ability to search through different songs.

![Capture](https://github.com/MMahar5/AudioPlayer_MM/assets/97249776/25321046-d08c-4a30-b217-a9ac6f78e940)



Sources for example:
https://www.tutorialspoint.com/android/android_mediaplayer.htm 
