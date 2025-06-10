public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiasRepository;

    // Criar novo anúncio
    public NoticiaDTO criarNoticias(NoticiaDTO dto) {
        // Convertendo a string recebida do DTO para o valor do enum Zona
        Zona zona = Zona.valueOf(dto.getZona());  // Agora, obtemos o valor do enum com o valor da string

        Noticia noticia = new Noticia(dto.getId(), dto.getTitulo(), dto.getTexto(), zona, dto.getImagem(), dto.getDataHoraCriacao());
        noticia = noticiasRepository.save(noticia);
        return new NoticiaDTO(noticia.getId(), dto.getTitulo(), dto.getTexto(), zona.name(), dto.getImagem(), dto.getDataHoraCriacao());
    }

    // Listar todos os anúncios
    public List<NoticiaDTO> listarNoticias() {
        List<Noticia> noticias = noticiasRepository.findAll();
        return noticias.stream()
                .map(n -> new NoticiaDTO(n.getId(), n.getTitulo(), n.getTexto(), n.getZona().name(), n.getImagem(), n.getDataHoraCriacao())) // Convertendo zona de enum para string
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<NoticiaDTO> buscarPorId(Long id) {
        Optional<Noticia> noticias = noticiasRepository.findById(id);
        return noticias.map(n -> new NoticiaDTO(n.getId(), n.getTitulo(), n.getTexto(), n.getZona().name(), n.getImagem(), n.getDataHoraCriacao()));
    }

    // Atualizar anúncio
    public Optional<NoticiaDTO> atualizarNoticias(Long id, NoticiaDTO dto) {
        Optional<Noticia> noticiasOpt = noticiasRepository.findById(id);
        if (noticiasOpt.isPresent()) {
            Noticia noticia = noticiasOpt.get();
            noticia.setTitulo(dto.getTitulo());
            noticia.setTexto(dto.getTexto());
            noticia.setImagem(dto.getImagem());
            noticia.setZona(Zona.valueOf(dto.getZona()));  // Atualizando a zona com o valor do enum
            noticia.setDataHoraCriacao(dto.getDataHoraCriacao());
            noticiasRepository.save(noticia);
            return Optional.of(new NoticiaDTO(noticia.getId(), noticia.getTitulo(), noticia.getTexto(), noticia.getZona().name(), noticia.getImagem(), noticia.getDataHoraCriacao()));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirNoticias(Long id) {
        if (noticiasRepository.existsById(id)) {
            noticiasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
