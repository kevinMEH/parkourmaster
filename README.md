# Parkour Master

![img.png](img.png)

Unfortunately, LitiEngine has lots of bugs, and I can't continue this project. I don't blame the devs, some things are maybe *broken* but it may just be because this is my first time working with a game engine, or the fact that I am too lazy / not smart enough to find workarounds / didn't bother reading the docs.

However, this project was not entirely fruitless. LitiEngine has taught me valuable skills and introduced me to ideas such as spritesheets, tiles, textures, tiled maps, game setups, environments, projects setups and more.

Problems:

- There is no idle state on the character, and the idle animation is never used. (isIdle() always return false, even if standing still.) I don't know how to set my character to use its idle animation when he is standing still.

- My character is supposed to only jump once, however, the algorithm that detects the ground/CollisionBox below the character is broken and gives my character the ability to jump twice.

- You can change jump direction midair. Changing jump direction midair makes it so that you can double jump.

- Gravity isn't acceleration, it's a constant velocity. Too lazy to fix.

- Plus some other smaller bugs. There's still a lot of features for me to implement and I'm very unfamiliar with how this all works.

Next steps: I will try another game engine, maybe libGDX.

Sorry.
