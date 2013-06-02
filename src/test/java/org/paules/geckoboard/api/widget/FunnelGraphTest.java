package org.paules.geckoboard.api.widget;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.paules.geckoboard.api.json.GraphType;

public class FunnelGraphTest {

    @Test
    public void testJson() throws JsonProcessingException, IOException {
        FunnelGraph widget = new FunnelGraph( "1234", GraphType.STANDARD, false );
        widget.addData( "Step 1", "100" );
        widget.addData( "Step 2", "200" );
        widget.addData( "Step 3", "300" );
        widget.addData( "Step 4", "500" );
        widget.addData( "Step 5", "10 mega" );

        ObjectMapper om = new ObjectMapper();
        JsonNode data = om.readTree( widget.toJson() );
        Assert.assertNotNull( data.get( "data" ) );
        JsonNode node = data.get( "data" );
        assertEquals( "standard", node.get( "type" ).asText() );
        assertEquals( "hide", node.get( "percentage" ).asText() );

        assertEquals( "Step 1", node.get( "item" ).get( 0 ).get( "label" ).asText() );
        assertEquals( "100", node.get( "item" ).get( 0 ).get( "value" ).asText() );

        assertEquals( "Step 2", node.get( "item" ).get( 1 ).get( "label" ).asText() );
        assertEquals( "200", node.get( "item" ).get( 1 ).get( "value" ).asText() );

        assertEquals( "Step 3", node.get( "item" ).get( 2 ).get( "label" ).asText() );
        assertEquals( "300", node.get( "item" ).get( 2 ).get( "value" ).asText() );

        assertEquals( "Step 4", node.get( "item" ).get( 3 ).get( "label" ).asText() );
        assertEquals( "500", node.get( "item" ).get( 3 ).get( "value" ).asText() );

        assertEquals( "Step 5", node.get( "item" ).get( 4 ).get( "label" ).asText() );
        assertEquals( "10 mega", node.get( "item" ).get( 4 ).get( "value" ).asText() );

    }

}
