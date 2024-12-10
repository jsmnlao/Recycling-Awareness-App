# RecycleBuddy: Recycling Awareness App
Members: Jasmine Lao, Galit Bolotin, Vedant Sawal, Bhavdeep Randhawa

The goal of our software engineering project is to design and create a mobile application that raises awareness about recycling by educating about proper recycling techniques and identifying which types of materials can be recycled. This app will be a smart recycling assistant where users can learn how to dispose of their waste with features such as:

- Using image recognition, identifying whether the given item is recyclable or not. The user can take a picture of the item and the app will guide the user which bin it goes in.
- Scanning barcodes or plastic recycling codes (triangle symbol) through image recognition
- Tracking user’s recycling stats in a recycling log to determine their environmental impact 
- Gamifying users’ recycling habits to engage users (ie. virtual pet or plant)
- Having a reward or point system for a certain amount of recycled items
- Challenging friends through leaderboards and community challenges 
- Learning through an information hub to search which specific items and materials are recyclable and what they become after going through the whole process.

## How to Run Backend Flask API
1. Open Anaconda.Navigator and start base (root) to open with Jupyter Notebook
2. Navigate to Recylcing_Predictor.ipynb and run all cells
3. Open Command Prompt (Windows) or Terminal (MacOS) and run this command: `curl -X POST -F "file=@<path_to_input_image>" http://<your_local_host_port>/predict`

## Documentation
- Design Mockup ([Figma Design](https://www.figma.com/design/k1GvkU4u922xV1wMBZmW6y/Recycling-App-Project?node-id=0-1&node-type=canvas&t=qiRZnTWPjNIoVh4H-0))
