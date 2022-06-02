package br.com.zup.caixadesupermercado.calculo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.caixadesupermercado.R
import br.com.zup.caixadesupermercado.databinding.ActivityCalculoBinding
import br.com.zup.caixadesupermercado.model.Produto

class CalculoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val produto = intent.getParcelableExtra("Produto") as Produto?
        val botaoRefazer = binding.btRefazerCompra

        produto?.let {
            val valorTotal = produto.getValor() * produto.getQuantidade()
            binding.tvTexto.text = getString(R.string.nome_produto, it.getNome())
            binding.tvResultado.text = getString(R.string.valor_total, "%.2f".format(valorTotal))
        }

        botaoRefazer.setOnClickListener {
            onBackPressed()
        }
    }
}