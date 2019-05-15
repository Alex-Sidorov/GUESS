# Analysis of the architecture and ways to improve it

### Comparison

Comparing the architectures **"as is"** and **"to be"**, the following differences can be distinguished:
 - In the **"as is"** architecture, GUI and the block of functionality, associated with interaction with the web server, are not separated.
 - In the **"as is"** architecture, GUI is not divided into separate pages.
 - In the **"as is"** architecture, mobile client interacts with the neural network and the back-end, while in the **"to be"** architecture client interacts only with the back-end.
 - In the **"to be"** architecture, back-end interacts with the neural network.

 The reason for the differences in architecture was the desire to quickly get a functioning application.
### Ways to improve

To improve the resulting architecture, the following tasks were set:
 - Split GUI and web controller.
 - Add pages controller.
 - Add business rule.
 - Interact only with back-end.
 - Add interaction logic with data-processing service.
 - Separate serializers from controllers.

More about the tasks you can see [here](https://trello.com/b/op6rP0dR/sprint) (marked in blue).
