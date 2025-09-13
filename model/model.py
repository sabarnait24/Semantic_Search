from sentence_transformers import SentenceTransformer

model = SentenceTransformer('all-MiniLM-L6-v2')

text = "Elon Musk founded SpaceX"
embedding = model.encode(text)

print(f"Text: {text}")
print(f"Embedding length: {len(embedding)}")
print(f"First 5 values: {embedding[:5]}")



