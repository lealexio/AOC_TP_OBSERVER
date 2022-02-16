# TP OBSERVER AOC

## Introduction
The goal is to build a sensor data dissemination service. 

The solution you will build must be based on the Active Object design pattern.

The implementation will allow to broadcast a stream of values to subscriber objects running in different threads from the service source.
The objective of this tutorial being the parallel implementation of Observer, the broadcasted data will be an increasing sequence of integers.

The counter will be incremented at fixed intervals.

The transmission of the information to the subscribers of the service will use a channel with a random transmission delay.

The architecture will therefore include:
- An active source (sensor), whose value evolves periodically
- A set of transmission channels with variable delays
- A set of Observer broadcast policies

![Intro](documentation/assets/intro.png)

## Documentation

### Documentation folder

The documentation folder contains subfolders each having documentation :

#### Assets
The assets folder contains the different diagrams such as the class diagram or the sequence diagram.

#### Executable
The executable folder contains an executable of the application as well as .bat and .sh scripts to execute it.

#### Javadoc
The javadoc contains the generated javadoc.


### Class diagram

![Class Diagram](documentation/assets/diagram_class.svg)

### Sequence diagrams

#### Era Diffusion
![Era Diffusion Sequence Diagram](documentation/assets/era_sequence_diagram.svg)

### Atomic Diffusion
![Atomic Diffusion Sequence Diagram](documentation/assets/atomic_sequence_diagram.svg)

#### Sequential Diffusion
![Sequential Diffusion Sequence Diagram](documentation/assets/sequential_sequence_diagram.svg)

## System description

## Era Diffusion
```java
public void execute() {
    // Update the displays
    for (Channel c : this.sensor.channels) {
    c.update();
    }
}
```
The action of era diffusion is simple, this diffusion is not blocking and just update the display.

## Atomic Diffusion
```java
public void execute() {
        // Update the displays
        ArrayList<Future<?>> futures = new ArrayList<>();
        for (Channel c : this.sensor.channels) {
            futures.add(c.update());
        }

        // Wait end of each future
        futures.forEach(i -> {
            try {
                i.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
```


## Sequential Diffusion
```java

```
## Tests

