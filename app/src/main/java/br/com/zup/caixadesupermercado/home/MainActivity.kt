package br.com.zup.caixadesupermercado.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.caixadesupermercado.calculo.CalculoActivity
import br.com.zup.caixadesupermercado.databinding.ActivityMainBinding
import br.com.zup.caixadesupermercado.model.Produto

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var produto: Produto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttonCalcular = binding.btCalcular
        buttonCalcular.setOnClickListener {
            if (validarEntradas()) {
                val intent = Intent(this, CalculoActivity::class.java).apply {
                    putExtra("Produto", produto)
                }
                limparCampos()
                startActivity(intent)
            }
        }
    }

    private fun validarEntradas(): Boolean {
        val nomeProduto = binding.etNomeProduto.text.toString().trim()
        val qtdProduto = binding.etQuantidadeProduto.text.toString().trim()
        val valorUnitario = binding.etValorUnitario.text.toString().trim()

        mensagemErro(
            nomeProduto.isNotBlank(),
            binding.etNomeProduto,
            "Insira um nome para o produto"
        )

        mensagemErro(
            qtdProduto.isNotBlank(),
            binding.etQuantidadeProduto,
            "Insira uma quantidade para o produto"
        )

        mensagemErro(
            valorUnitario.isNotBlank(),
            binding.etValorUnitario,
            "Insira um valor unitário para o produto"
        )
        if (nomeProduto.isNotBlank() && qtdProduto.isNotBlank() && valorUnitario.isNotBlank()) {
            produto = salvarProduto(nomeProduto, qtdProduto.toInt(), valorUnitario.toDouble())
        }
        return nomeProduto.isNotBlank() && qtdProduto.isNotBlank() && valorUnitario.isNotBlank()
    }

    private fun mensagemErro(valido: Boolean, editText: EditText, msg: String) {
        editText.error = if (valido) null else msg
    }

    private fun salvarProduto(nome: String, qtd: Int, valor: Double): Produto {
        return Produto(nome, qtd, valor)
    }

    private fun limparCampos() {
        binding.etNomeProduto.text.clear()
        binding.etQuantidadeProduto.text.clear()
        binding.etValorUnitario.text.clear()
    }
}