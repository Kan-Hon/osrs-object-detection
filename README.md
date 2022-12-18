# Object Detection in OSRS using Python and Java
Object detection using YoloV5 using Python and Java. This post is purely for educational purposes.

In 2019, an OpenAI project known as OpenAI Five claimed the world-first AI system trained to play a complex strategy game, beating the world champion team in a multiplayer video game called Dota 2. Intrigued, I set out to explore the capabilities of AI at playing video games.

I turned to a game which I have played for thousands of hours during my childhood years: Runescape - a mass multiplayer game with basic 3d graphics. 

### Object detection

To identify objects in the game, I decided to build a object detection model that rely on screen scraping. To automate the mundane process of collecting labels necessary for the model, I used a software that relied on code injection to extract details from the game. I walked around the in-game areas, programming the software to capture in-game screenshots periodically as I changed the camera's pitch and yaw.

<img src="https://user-images.githubusercontent.com/66121721/208275066-9158a026-f8ad-4aad-9f0c-ebec87f7950c.png" alt="Figure 1 - Screen scraping example"  width="400"/>
<em> Figure 1 - Screen scraping example  </em>


### Modelling 

I have chosen YoloV5 as the object detection model for this project, due to its efficiency and speed to achieve a respectable fps during inference for my PC specifications (Nvidia RTX 3060Ti 8GB). 

I first trained the model on 100 in-game screenshots, teaching the model to identify 'Cows' against the background. I have also included additional information to whether the 'Cow' was in combat with another player.

<img src="https://user-images.githubusercontent.com/66121721/208278268-403abffc-a178-4504-a395-76e69e0f34ac.png" alt="Figure 2 - Phase 1 Precision-Recall curve"  width="600"/>
<em> Figure 2 - Phase 1 Precision-Recall curve </em>

\
The initial model performed well for all the classes except one - "Cow - Unavailable". I hypothesised that this was due to the lack of training examples associated with this class, and collected more in-game screenshots for training. This time, I ensured there were sufficient training samples for all classes.

<img src="https://user-images.githubusercontent.com/66121721/208278430-19163ab3-9346-4b2d-a6c7-99573a841c39.png" alt="Figure 3 - Phase 2 Precision-Recall curve"  width="600"/>
<em> Figure 3 - Phase 2 Precision-Recall curve </em>

\
The improved model achieved high precision and recall for all the classes of interest, reaching mAP50 of 0.995. The model also attained an average inference speed of 6.9ms per image with GPU support, making it capable of scraping screen data in real-time at 144fps. 

<img src="https://user-images.githubusercontent.com/66121721/208278519-74cb71f1-600e-47f6-816a-f22de818372f.png" alt="Figure 4 - Inference result"  width="600"/>

