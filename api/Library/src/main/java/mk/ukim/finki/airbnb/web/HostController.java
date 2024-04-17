package mk.ukim.finki.airbnb.web;
import mk.ukim.finki.airbnb.model.Host;
import mk.ukim.finki.airbnb.model.dto.HostDto;
import mk.ukim.finki.airbnb.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping("")
    public List<Host> getAllAuthors()
    {
        return hostService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable Long id)
    {
        return hostService.findById(id).map(accomm -> ResponseEntity.ok().body(accomm))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> addHost(@RequestBody HostDto hostDto)
    {
        return hostService.create(hostDto.getName(),
                        hostDto.getSurname(),
                        hostDto.getCountryId())
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Host> deleteHost(@PathVariable Long id)
    {
        return hostService.delete(id)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Host> editHost(@PathVariable Long id, @RequestBody HostDto hostDto)
    {
        return hostService.update(id, hostDto.getName(), hostDto.getSurname(), hostDto.getCountryId())
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

