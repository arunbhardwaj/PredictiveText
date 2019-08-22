# PredictiveText

### What I learned:
- Studied and implemented a basic predictive text algorithm that form the basis for machine learning and autocomplete functions in search and mobile phones
- Studied and implemented interfaces to reduce common code, avoid duplicating code, and supply default functionality when possible
- Studied and implemented abstract classes to allow for extensibility of future code, and that can capture common code that an interface cannot
- Learned and implemented HashMaps to improve algorithm efficiency


### <u>Errors I came across</u>
- Generating random text was different than from other examples. This was due to an implementation difference. For cases where n > 1 words were used to generate random text, I had generated each word up to n randomly, thereby creating starting keys that were not contained within the source text. Other implementations had sourced the starting key from a <b>singular</b> random selection of text containing n words. 
- One of my first implementations used mutable String[] arrays as keys to the HashMap. After switching the keys to immutable Strings, I had realized that the issue was with the mutability of the keys.
