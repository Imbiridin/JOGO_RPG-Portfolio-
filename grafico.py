import sys
import matplotlib.pyplot as plt
import numpy as np

categorias = ['Inteligencia', 'Força', 'Velocidade', 'Resistência', 'Habilidade']

valores_texto = sys.argv[1]
valores = [float(v) for v in valores_texto.split(',')]

valores_fechados = valores + valores[:1]

angulos = np.linspace(0, 2 * np.pi, len(categorias), endpoint=False).tolist()
angulos += angulos[:1]

fig, ax = plt.subplots(figsize=(6, 6), subplot_kw=dict(polar=True))

ax.plot(angulos, valores_fechados, color='blue', linewidth=2, linestyle='solid')
ax.fill(angulos, valores_fechados, color='blue', alpha=0.3)

ax.set_xticks(angulos[:-1])
ax.set_xticklabels(categorias)
ax.set_ylim(0, 10)

plt.title("Habilidades do Personagem")
plt.show()